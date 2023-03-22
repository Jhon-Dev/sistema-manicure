package com.dev.manicure.entity;

import com.dev.manicure.entity.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_SERVICE_PROVIED")
public class ServiceProvied {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SERVICE_TYPE", nullable = false, length = 75)
    @ElementCollection(targetClass = ServiceType.class)
    private List<ServiceType> serviceType;

    @Column(name = "DATA", nullable = false)
    private Date data;

    @Column(name = "VALUE")
    private Integer valor;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

}
