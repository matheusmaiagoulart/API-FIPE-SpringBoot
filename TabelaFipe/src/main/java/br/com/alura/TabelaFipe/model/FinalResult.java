package br.com.alura.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record FinalResult(@JsonAlias("TipoVeiculo") String tipoVeiculo,
                          @JsonAlias("Valor") String valor,
                          @JsonAlias("Marca") String marca,
                          @JsonAlias("Modelo") String modelo,
                          @JsonAlias("AnoModelo") int anoModelo,
                          @JsonAlias("Combustivel") String combustivelTipo,
                          @JsonAlias("CodigoFipe") String codigoFipe,
                          @JsonAlias("MesReferencia") String mesReferencia,
                          @JsonAlias("SiglaCombustivel") String siglaCombustivel) {
    @Override
    public String toString() {
        return  "Tipo do Veículo: " + tipoVeiculo + "\n" +
                "Valor: " + valor + "\n" +
                "Marca: " + marca + "\n" +
                "Modelo: " + modelo + "\n" +
                "Ano Modelo: " + anoModelo + "\n" +
                "Combustível: " + combustivelTipo + "\n" +
                "Código Fipe: " + codigoFipe + "\n" +
                "Mês Referência: " + mesReferencia + "\n" +
                "Sigla Combustível: " + siglaCombustivel;



    }
}

