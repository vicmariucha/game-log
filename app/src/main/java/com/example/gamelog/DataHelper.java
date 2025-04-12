package com.example.gamelog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataHelper {
    private static List<String> avaliacoes = new ArrayList<>();
    private static List<String> usuarios = Arrays.asList("Bruno", "Fulano", "Fulanot23", "FulanocGamer");
    private static String usuarioLogado = "Usuário";

    public static List<String> getAvaliacoes() {
        return avaliacoes;
    }

    public static void addAvaliacao(String avaliacao) {
        avaliacoes.add(avaliacao);
    }

    public static List<String> buscarUsuarios(String termo) {
        List<String> resultados = new ArrayList<>();
        for (String usuario : usuarios) {
            if (usuario.toLowerCase().contains(termo.toLowerCase())) {
                resultados.add(usuario);
            }
        }
        return resultados;
    }

    public static String getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(String usuario) {
        usuarioLogado = usuario;
    }
}