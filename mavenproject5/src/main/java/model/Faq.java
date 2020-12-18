/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fabien
 */
public class Faq {
    
    private int idQuestion;
    private int idUserQuestion;
    private String question;
    private int idUserReponse;
    private String reponse;

    public Faq(int idQuestion, int idUserQuestion, String question, int idUserReponse, String reponse) {
        this.idQuestion = idQuestion;
        this.idUserQuestion = idUserQuestion;
        this.question = question;
        this.idUserReponse = idUserReponse;
        this.reponse = reponse;
    }

    public Faq() {
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdUserQuestion() {
        return idUserQuestion;
    }

    public void setIdUserQuestion(int idUserQuestion) {
        this.idUserQuestion = idUserQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getIdUserReponse() {
        return idUserReponse;
    }

    public void setIdUserReponse(int idUserReponse) {
        this.idUserReponse = idUserReponse;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    
    
    
    
    
    
}
