package br.com.alura.TabelaFipe.principal;

import br.com.alura.TabelaFipe.model.Anos;
import br.com.alura.TabelaFipe.model.DadosVeiculos;
import br.com.alura.TabelaFipe.model.FinalResult;
import br.com.alura.TabelaFipe.model.Modelos;
import br.com.alura.TabelaFipe.service.ConsumoAPI;
import br.com.alura.TabelaFipe.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

        Scanner scanner = new Scanner(System.in);
        private ConsumoAPI consumo = new ConsumoAPI();
        private ConverteDados converteDados = new ConverteDados();
        private final String URL_Base = "https://parallelum.com.br/fipe/api/v1/";
        private String ENDERECO;
        public void exibeMenu() throws JsonProcessingException {

            var menu = """
                    ***OPÇÕES***
                    Carros
                    Motos
                    Caminhões
                    """;
            System.out.println(menu);
            System.out.println("Digite qual a opção desejada para consulta:");
            var opcao = scanner.nextLine();
            if(opcao.toLowerCase().contains("car")){
                ENDERECO = URL_Base + "carros/marcas/";
            } else if (opcao.toLowerCase().contains("mot")){
                ENDERECO = URL_Base + "motos/marcas/";
            } else if (opcao.toLowerCase().contains("camin")) {
                ENDERECO = URL_Base + "caminhoes/marcas/";
            }
            var json = consumo.obterDados(ENDERECO);
            var marcas = converteDados.obterLista(json, DadosVeiculos.class);
            marcas.stream()
                    .sorted(Comparator.comparing(DadosVeiculos::codigo))
                    .forEach(System.out::println);
            System.out.println("Escolha a Marca desejada:");
            var marcaEscohida = scanner.nextLine();


            ENDERECO = ENDERECO + marcaEscohida + "/modelos/";
            json = consumo.obterDados(ENDERECO);
            var veiculosLista = converteDados.obterDados(json, Modelos.class);
            System.out.println("\nDigite o inicio do modelo que você quer encontrar: ");
            var modeloFiltro = scanner.nextLine();
            List<DadosVeiculos> veiculosFiltrados = veiculosLista.modelos().stream()
                            .filter(m -> m.nome().toLowerCase().contains(modeloFiltro.toLowerCase()))
                                    .collect(Collectors.toList());
            System.out.println("Modelos filtrados: ");
            veiculosFiltrados.forEach(System.out::println);
            System.out.println("Agora digite o código do veículo que deseja consultar: ");
            var modeloCodigo = scanner.nextLine();


            ENDERECO = ENDERECO + modeloCodigo + "/anos/";
            json = consumo.obterDados(ENDERECO);
            var anosLista = converteDados.obterLista(json, DadosVeiculos.class);
            anosLista.stream()
                    .sorted(Comparator.comparing(DadosVeiculos::codigo).reversed())
                    .forEach(System.out::println);


            System.out.println("Escolha uma opção: ");
            var anoVeiculo = scanner.nextLine();

            ENDERECO = ENDERECO + anoVeiculo + "/";
            json = consumo.obterDados(ENDERECO);
            var finalResultList = converteDados.obterDados(json, FinalResult.class);
            System.out.println(finalResultList);


        }
    }

