package br.com.bosko.boskoapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_clg_progress")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nr_progresso")
    private Integer progress;

    @Column(name = "ds_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;

    public Progress(){}

    public Progress(Integer progress, Status status, Book book) {
        this.progress = progress;
        this.status = status;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
