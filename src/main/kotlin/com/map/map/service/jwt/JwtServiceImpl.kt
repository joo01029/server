package com.map.map.service.jwt

import com.map.map.domain.entity.User
import com.map.map.domain.repository.UserRepo
import com.map.map.enum.JwtType
import io.jsonwebtoken.*
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Service
class JwtServiceImpl : JwtService {

    @Autowired
    lateinit var userRepo: UserRepo

    @Value("\${jwt.auth.access}")
    private val secretAccessKey: String? = null

    @Value("\${jwt.auth.refresh}")
    private val secretRefreshKey: String? = null

    val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256

    /**
     * 토큰 생성
     */
    override fun createToken(id: String, authType: JwtType): String {
        var expiredAt = Date()

        val secretKey: String? = when (authType) {
            JwtType.ACCESS -> {
                expiredAt = Date(expiredAt.time + 1000 * 60 * 60)
                secretAccessKey
            }
            JwtType.REFRESH -> {
                expiredAt = Date(expiredAt.time + 1000 * 60 * 60 * 24 * 7)
                secretRefreshKey
            }
            else -> {
                throw HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "토큰 생성 에러")
            }
        }

        val signInKey = SecretKeySpec(secretKey!!.toByteArray(), signatureAlgorithm.jcaName)

        val headerMap: MutableMap<String, Any> = HashMap()

        headerMap["typ"] = "JWT"
        headerMap["alg"] = "HS256"

        val map: MutableMap<String, Any> = HashMap()

        map["id"] = id
        map["authType"] = authType

        return Jwts.builder()
            .setHeaderParams(headerMap)
            .setClaims(map)
            .setExpiration(expiredAt)
            .signWith(signInKey)
            .compact()
    }

    /**
     * 토큰 유효 검증
     */
    override fun validateToken(token: String?): User? {
        try {
            val signInKey: Key = SecretKeySpec(secretAccessKey!!.toByteArray(), signatureAlgorithm.jcaName)
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(signInKey)
                .build()
                .parseClaimsJws(token)
                .body

            if (claims["authType"].toString() != "ACCESS") {
                throw HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 타입 아님")
            }

            return userRepo.findById(claims["id"].toString())
                ?: throw HttpClientErrorException(HttpStatus.NOT_FOUND, "유저 없음")
        } catch (e: ExpiredJwtException) {
            throw HttpClientErrorException(HttpStatus.GONE, "토큰 만료")
        } catch (e: SignatureException) {
            throw HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 위조")
        } catch (e: MalformedJwtException) {
            throw HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 위조")
        } catch (e: IllegalArgumentException) {
            throw HttpClientErrorException(HttpStatus.BAD_REQUEST, "토큰 없음")
        } catch (e: HttpClientErrorException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류")
        }
    }

    /**
     * 토큰 갱신
     */
    override fun refreshToken(refreshToken: String?): String? {
        try {
            if (refreshToken == null || refreshToken.trim().isEmpty()) {
                throw HttpClientErrorException(HttpStatus.BAD_REQUEST, "검증 오류")
            }

            val signInKey = SecretKeySpec(secretRefreshKey!!.toByteArray(), signatureAlgorithm.jcaName)
            val claims = Jwts.parserBuilder()
                .setSigningKey(signInKey)
                .build()
                .parseClaimsJws(refreshToken)
                .body

            if (claims["authType"].toString() != "REFRESH") {
                throw HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 타입 아님")
            }

            val user: User = userRepo.findById(claims["id"].toString())
                ?: throw HttpClientErrorException(HttpStatus.NOT_FOUND, "유저 없음.")

            return this.createToken(user.id!!, JwtType.ACCESS)
        } catch (e: ExpiredJwtException) {
            throw HttpClientErrorException(HttpStatus.GONE, "토큰 만료")
        } catch (e: SignatureException) {
            throw HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 위조")
        } catch (e: MalformedJwtException) {
            throw HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 위조")
        } catch (e: IllegalArgumentException) {
            throw HttpClientErrorException(HttpStatus.BAD_REQUEST, "토큰 없음")
        } catch (e: HttpClientErrorException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류")
        }
    }
}