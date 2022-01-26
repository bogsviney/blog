package com.nazarov.blog.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "tbl_message"
)
public class Message {

    @Id
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
    )

    private long messageId;
    private String title;
    private String content;
    private boolean star;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "message", orphanRemoval = true)
    private List<Comment> comments;

    @JsonManagedReference
    public List<Comment> getComments() {
        return comments;
    }
}
