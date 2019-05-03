package com.contas.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.contas.util.UtilDate;

@Entity
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String descricao;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dataVencimento;
	
	@NumberFormat(pattern = "#,##0.00")
	@NotNull
	private BigDecimal valor;
	
	private String parcelas;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private StatusPagamento statusPagamento;
	
	public Conta() {}
	
	public Conta(String descricao, Date dataVencimento, BigDecimal valor, String parcelas,
			StatusPagamento statusPagamento) {
		this.descricao = descricao;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.parcelas = parcelas;
		this.statusPagamento = statusPagamento;
	}

	public boolean isPaga() {
		return this.statusPagamento.getDescricao().equals(StatusPagamento.PAGO.getDescricao()); 
	}
	
	public boolean isVencida() {
		return contaNaoPagaEVencida();
	}

	private boolean contaNaoPagaEVencida() {
		return !isPaga() && UtilDate.convertDateToLocalDate(this.dataVencimento).isBefore(LocalDate.now());
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}
	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
	public String getParcelas() {
		return parcelas;
	}
	public void setParcelas(String parcelas) {
		this.parcelas = parcelas;
	}	
	
	@Override
	public String toString() {
		return "Conta [id=" + id + ", descricao=" + descricao + ", dataVencimento=" + dataVencimento + ", valor="
				+ valor + ", statusPagamento=" + statusPagamento + "]";
	}
}
