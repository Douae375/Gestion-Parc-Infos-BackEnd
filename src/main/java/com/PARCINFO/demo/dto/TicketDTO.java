package com.PARCINFO.demo.dto;

import com.PARCINFO.demo.entity.Equipement;
import com.PARCINFO.demo.entity.Users;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDTO {
    private Integer id;

    private String type;
    private Date date;
    private String description;
    private Equipement equipement;
    private Users user;
    private String statut;
    private Long ticketcount;

    public Long getTicketcount() {
        return ticketcount;
    }

    public void setTicketcount(Long ticketcount) {
        this.ticketcount = ticketcount;
    }

    public TicketDTO() {
    }

    public TicketDTO(String statut, Long ticketcount) {
        this.statut = statut;
        this.ticketcount = ticketcount;
    }

    public TicketDTO(Integer id, String type, Date date, String description, Equipement equipement, Users user, String statut) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.description = description;
        this.equipement = equipement;
        this.user = user;
        this.statut = statut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }



}
