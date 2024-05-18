package TAREFAS78;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Tarefa {
    private String descricao;
    private boolean concluida;

    public Tarefa(String descricao) {
        this.descricao = descricao;
        this.concluida = false;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void marcarConcluida() {
        this.concluida = true;
    }

    public void editarDescricao(String novaDescricao) {
        this.descricao = novaDescricao;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "descricao='" + descricao + '\'' +
                ", concluida=" + concluida +
                '}';
    }
}

public class GERENCIAL {
    private static final String FILENAME = "tarefas.txt";

    public static void main(String[] args) {
        ArrayList<Tarefa> tarefas = carregarTarefas();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Mostrar todas as tarefas");
            System.out.println("3. Marcar tarefa como concluída");
            System.out.println("4. Editar descrição da tarefa");
            System.out.println("5. Excluir tarefa");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                System.out.print("Digite a descrição da tarefa: ");
                scanner.nextLine();
                String descricao = scanner.nextLine();
                Tarefa tarefa = new Tarefa(descricao);
                tarefas.add(tarefa);
                System.out.println("Tarefa adicionada com sucesso!");
            } else if (opcao == 2) {
                mostrarTodasTarefas(tarefas);
            } else if (opcao == 3) {
                marcarTarefaConcluida(scanner, tarefas);
            } else if (opcao == 4) {
                editarDescricaoTarefa(scanner, tarefas);
            } else if (opcao == 5) {
                excluirTarefa(scanner, tarefas);
            } else if (opcao == 6) {
                salvarTarefas(tarefas);
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static ArrayList<Tarefa> carregarTarefas() {
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        try {
            File file = new File(FILENAME);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                tarefas = (ArrayList<Tarefa>) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    private static void salvarTarefas(ArrayList<Tarefa> tarefas) {
        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tarefas);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarTodasTarefas(ArrayList<Tarefa> tarefas) {
        System.out.println("Tarefas:");
        for (int i = 0; i < tarefas.size(); i++) {
            System.out.println((i + 1) + ". " + tarefas.get(i));
        }
    }

    private static void marcarTarefaConcluida(Scanner scanner, ArrayList<Tarefa> tarefas) {
        System.out.print("Digite o número da tarefa que deseja marcar como concluída: ");
        int numTarefa = scanner.nextInt();
        if (numTarefa >= 1 && numTarefa <= tarefas.size()) {
            Tarefa tarefa = tarefas.get(numTarefa - 1);
            tarefa.marcarConcluida();
            System.out.println("Tarefa marcada como concluída com sucesso!");
        } else {
            System.out.println("Número de tarefa inválido.");
        }
    }

    private static void editarDescricaoTarefa(Scanner scanner, ArrayList<Tarefa> tarefas) {
        System.out.print("Digite o número da tarefa que deseja editar: ");
        int numTarefa = scanner.nextInt();
        scanner.nextLine();
        if (numTarefa >= 1 && numTarefa <= tarefas.size()) {
            System.out.print("Digite a nova descrição da tarefa: ");
            String novaDescricao = scanner.nextLine();
            Tarefa tarefa = tarefas.get(numTarefa - 1);
            tarefa.editarDescricao(novaDescricao);
            System.out.println("Descrição da tarefa editada com sucesso!");
        } else {
            System.out.println("Número de tarefa inválido.");
        }
    }

    private static void excluirTarefa(Scanner scanner, ArrayList<Tarefa> tarefas) {
        System.out.print("Digite o número da tarefa que deseja excluir: ");
        int numTarefa = scanner.nextInt();
        if (numTarefa >= 1 && numTarefa <= tarefas.size()) {
            tarefas.remove(numTarefa - 1);
            System.out.println("Tarefa excluída com sucesso!");
        } else {
            System.out.println("Número de tarefa inválido.");
        }
    }
}