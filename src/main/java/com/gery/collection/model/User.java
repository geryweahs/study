package com.gery.collection.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.client.protocol.ScoredEntry;

import java.io.Serializable;
import java.util.List;

/**
 * @program: gery-demo-2023
 * @ClassName User
 * @description:
 * @author: yaowenhua
 * @create: 2023-08-16 09:33
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Cloneable, Serializable {

    private String id;

    private String code;

    private Integer age;

    private Integer type;

    private List<Score> scoreList;

    @Override
    public User clone() {
        try {
            User clone = (User) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Score implements Cloneable,Serializable{

        private Integer oneScore;
        private Integer twoScore;

        @Override
        public Score clone() {
            try {
                Score clone = (Score) super.clone();
                // TODO: copy mutable state here, so the clone can't change the internals of the original
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
}
