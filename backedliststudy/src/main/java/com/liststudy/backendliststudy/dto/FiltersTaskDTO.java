package com.liststudy.backendliststudy.dto;

public class FiltersTaskDTO {

    private Integer idTopic;
    private Double priceMin;
    private Double priceMax;

    public String toString() {
        return "[idTopic:"+idTopic+",priceMin:"+priceMin+",priceMax:"+priceMax+"]";
    }


    public Double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public Double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(Integer idTopic) {
        this.idTopic = idTopic;
    }
}
