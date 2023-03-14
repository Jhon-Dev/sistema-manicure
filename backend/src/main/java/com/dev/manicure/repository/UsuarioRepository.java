package com.dev.manicure.repository;

import com.dev.manicure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUserName(String userName);

    @Query("SELECT u FROM Usuario u JOIN FETCH u.roles WHERE u.userName = :userName")
    Usuario findByUserNameFetchRoles(@Param("userName") String userName);

}
