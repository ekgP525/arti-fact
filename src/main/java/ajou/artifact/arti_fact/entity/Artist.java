package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "artist")
@Getter
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;

    @Column(name = "name")
    private String name;
}