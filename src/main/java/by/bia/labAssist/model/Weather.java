package by.bia.labAssist.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;
    private Float k53_10_temperature;
    private Float k53_16_temperature;
    private Float k42_10_temperature;
    private Float k42_16_temperature;
    private Float k53_10_humidity;
    private Float k53_16_humidity;
    private Float k42_10_humidity;
    private Float k42_16_humidity;
    private Integer k53_10_pressure;
    private Integer k53_16_pressure;
    private Integer k42_10_pressure;
    private Integer k42_16_pressure;

    @Transient
    private String dateS;
   /* @Transient
    private List<Float> allTemperature;
    @Transient
    private List<Float> allHumidity;
    @Transient
    private List<Integer> allPressure;*/


    public Weather() {
    }

    public Weather(LocalDate date, Float k53_10_temperature, Float k53_16_temperature, Float k42_10_temperature,
                   Float k42_16_temperature, Float k53_10_humidity, Float k53_16_humidity, Float k42_10_humidity,
                   Float k42_16_humidity, Integer k53_10_pressure, Integer k53_16_pressure, Integer k42_10_pressure,
                   Integer k42_16_pressure) {
        this.date = date;
        this.k53_10_temperature = k53_10_temperature;
        this.k53_16_temperature = k53_16_temperature;
        this.k42_10_temperature = k42_10_temperature;
        this.k42_16_temperature = k42_16_temperature;
        this.k53_10_humidity = k53_10_humidity;
        this.k53_16_humidity = k53_16_humidity;
        this.k42_10_humidity = k42_10_humidity;
        this.k42_16_humidity = k42_16_humidity;
        this.k53_10_pressure = k53_10_pressure;
        this.k53_16_pressure = k53_16_pressure;
        this.k42_10_pressure = k42_10_pressure;
        this.k42_16_pressure = k42_16_pressure;
        //this.dateS = date.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
    }

    public List<Float> getAllTemperature() {
        List<Float> allTemperature = new ArrayList<>();
        allTemperature.add(k53_10_temperature);
        allTemperature.add(k53_16_temperature);
        allTemperature.add(k42_10_temperature);
        allTemperature.add(k42_16_temperature);
        return allTemperature;
        /*this.allTemperature = new ArrayList<>();
        this.allTemperature.add(k53_10_temperature);
        this.allTemperature.add(k53_16_temperature);
        this.allTemperature.add(k42_10_temperature);
        this.allTemperature.add(k42_16_temperature);
        return allTemperature;*/
    }

    /*public void setAllTemperature(List<Float> allTemperature) {
        this.allTemperature = allTemperature;
    }*/

    public List<Float> getAllHumidity() {
        List<Float> allHumidity = new ArrayList<>();
        allHumidity.add(k53_10_humidity);
        allHumidity.add(k53_16_humidity);
        allHumidity.add(k42_10_humidity);
        allHumidity.add(k42_16_humidity);
        return allHumidity;
        /*this.allHumidity = new ArrayList<>();
        this.allHumidity.add(k53_10_humidity);
        this.allHumidity.add(k53_16_humidity);
        this.allHumidity.add(k42_10_humidity);
        this.allHumidity.add(k42_16_humidity);
        return allHumidity;*/
    }

    /*public void setAllHumidity(List<Float> allHumidity) {
        this.allHumidity = allHumidity;
    }*/

    public List<Integer> getAllPressure() {
        List<Integer> allPressure = new ArrayList<>();
        allPressure.add(k53_10_pressure);
        allPressure.add(k53_16_pressure);
        allPressure.add(k42_10_pressure);
        allPressure.add(k42_16_pressure);
        return allPressure;
        /*this.allPressure = new ArrayList<>();
        this.allPressure.add(k53_10_pressure);
        this.allPressure.add(k53_16_pressure);
        this.allPressure.add(k42_10_pressure);
        this.allPressure.add(k42_16_pressure);
        return allPressure;*/
    }

    /*public void setAllPressure(List<Integer> allPressure) {
        this.allPressure = allPressure;
    }*/

    public String getDateS() {
        /*if(dateS == null){
            this.dateS = this.date.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        }*/
        this.dateS = this.date.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = this.date.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getK53_10_temperature() {
        return k53_10_temperature;
    }

    public void setK53_10_temperature(Float k53_10_temperature) {
        this.k53_10_temperature = k53_10_temperature;
    }

    public Float getK53_16_temperature() {
        return k53_16_temperature;
    }

    public void setK53_16_temperature(Float k53_16_temperature) {
        this.k53_16_temperature = k53_16_temperature;
    }

    public Float getK42_10_temperature() {
        return k42_10_temperature;
    }

    public void setK42_10_temperature(Float k42_10_temperature) {
        this.k42_10_temperature = k42_10_temperature;
    }

    public Float getK42_16_temperature() {
        return k42_16_temperature;
    }

    public void setK42_16_temperature(Float k42_16_temperature) {
        this.k42_16_temperature = k42_16_temperature;
    }

    public Float getK53_10_humidity() {
        return k53_10_humidity;
    }

    public void setK53_10_humidity(Float k53_10_humidity) {
        this.k53_10_humidity = k53_10_humidity;
    }

    public Float getK53_16_humidity() {
        return k53_16_humidity;
    }

    public void setK53_16_humidity(Float k53_16_humidity) {
        this.k53_16_humidity = k53_16_humidity;
    }

    public Float getK42_10_humidity() {
        return k42_10_humidity;
    }

    public void setK42_10_humidity(Float k42_10_humidity) {
        this.k42_10_humidity = k42_10_humidity;
    }

    public Float getK42_16_humidity() {
        return k42_16_humidity;
    }

    public void setK42_16_humidity(Float k42_16_humidity) {
        this.k42_16_humidity = k42_16_humidity;
    }

    public Integer getK53_10_pressure() {
        return k53_10_pressure;
    }

    public void setK53_10_pressure(Integer k53_10_pressure) {
        this.k53_10_pressure = k53_10_pressure;
    }

    public Integer getK53_16_pressure() {
        return k53_16_pressure;
    }

    public void setK53_16_pressure(Integer k53_16_pressure) {
        this.k53_16_pressure = k53_16_pressure;
    }

    public Integer getK42_10_pressure() {
        return k42_10_pressure;
    }

    public void setK42_10_pressure(Integer k42_10_pressure) {
        this.k42_10_pressure = k42_10_pressure;
    }

    public Integer getK42_16_pressure() {
        return k42_16_pressure;
    }

    public void setK42_16_pressure(Integer k42_16_pressure) {
        this.k42_16_pressure = k42_16_pressure;
    }

}
