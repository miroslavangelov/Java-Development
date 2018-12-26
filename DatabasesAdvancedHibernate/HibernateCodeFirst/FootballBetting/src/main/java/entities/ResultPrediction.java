package entities;

import enums.Prediction;

import javax.persistence.*;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction {
    private int id;
    private Prediction prediction;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "prediction")
    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
}
