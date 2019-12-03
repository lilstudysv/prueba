package com.liststudy.backendliststudy.dto;

import com.liststudy.backendliststudy.model.EnumKindTask;
import com.liststudy.backendliststudy.model.EnumStateTask;
import com.liststudy.backendliststudy.model.EnumTopicTask;

public class TaskDTO {
        private Long id;
        private EnumStateTask state;
        private EnumTopicTask topic;
        private EnumKindTask kind;
        private String title;
        private String description;
        private Double price;
        private Long creator;

        public String toString() {
            return "[id:"+id+",state:"+state+",topic:"+topic+",kind:"+kind+",title:"+title
                    +",description:"+description+",price:"+price+",creator:"+creator+"]";
        }

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public EnumStateTask getState() {
            return state;
        }
        public void setState(EnumStateTask state) {
            this.state = state;
        }
        public EnumTopicTask getTopic() {
            return topic;
        }
        public void setTopic(EnumTopicTask topic) {
            this.topic = topic;
        }
        public EnumKindTask getKind() {
            return kind;
        }
        public void setKind(EnumKindTask kind) {
            this.kind = kind;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public Double getPrice() {
            return price;
        }
        public void setPrice(Double price) {
            this.price = price;
        }
        public Long getCreator() {
            return creator;
        }
        public void setCreator(Long creator) {
            this.creator = creator;
        }

}
