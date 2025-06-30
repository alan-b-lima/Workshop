package edu.ajan.model.workshop;

import edu.ajan.model.persistence.Caretaker;
import edu.ajan.model.persistence.InstanceCountState;
import edu.ajan.model.persistence.Snapshot;
import edu.ajan.model.workshop.common.Registry;
import edu.ajan.model.workshop.financial.Financial;
import edu.ajan.model.workshop.service.Scheduler;
import edu.ajan.model.workshop.staff.MemberBase;
import edu.ajan.model.workshop.stock.Stock;

/**
 * Classe que representa a oficina mecânica, contendo todos os seus sistemas.
 * 
 * @author Alan Lima
 */
public class Workshop {

    /**
     * Registro da oficina, contendo informações sobre o estabelecimento.
     */
    private Registry registry;

    /**
     * Base de usuários da oficina, contendo informações sobre os membros.
     */
    private MemberBase memberbase;

    /**
     * Sistema financeiro da oficina, responsável por gerenciar finanças.
     */
    private Financial financial;

    /**
     * Sistema de agendamento da oficina, responsável por gerenciar serviços e
     * horários.
     */
    private Scheduler scheduler;

    /**
     * Estoque da oficina, responsável por gerenciar produtos e suprimentos.
     */
    private Stock stock;

    private static Workshop instance;

    /**
     * Construtor padrão da oficina.
     */
    private Workshop() {
        this.registry = new Registry();
        this.memberbase = new MemberBase();
        this.financial = new Financial();
        this.scheduler = new Scheduler();
        this.stock = new Stock();
    }

    /**
     * Retorna a instância única da oficina.
     * 
     * @return instância única da oficina.
     */
    public static Workshop workshop() {
        return instance;
    }

    /**
     * Carrega o estado da oficina a partir do curador de snapshots.
     * Se não houver nenhum snapshot salvo, cria uma nova instância da oficina.
     */
    public static void load() {

        Snapshot snapshot = Caretaker.caretaker().loadSnapshot();
        if (snapshot == null) {
            instance = new Workshop();
            InstanceCountState.restore(null);
            return;
        }

        instance = snapshot.workshop();
        InstanceCountState.restore(snapshot.instanceCountState());
    }

    /**
     * Salva o estado atual da oficina e captura um snapshot.
     */
    public static void save() {

        if (instance == null) {
            return;
        }

        Snapshot snapshot = new Snapshot(Workshop.workshop(), InstanceCountState.capture());
        Caretaker.caretaker().saveSnapshot(snapshot);
        Caretaker.save();
    }

    /**
     * Retorna o registro da oficina.
     * 
     * @return o registro da oficina.
     */
    public Registry registry() {
        return registry;
    }

    /**
     * Retorna a base de membros da oficina.
     * 
     * @return a base de membros da oficina.
     */
    public MemberBase memberbase() {
        return memberbase;
    }

    /**
     * Retorna o sistema financeiro da oficina.
     * 
     * @return o sistema financeiro da oficina.
     */
    public Financial financial() {
        return financial;
    }

    /**
     * Retorna o sistema de agendamento da oficina.
     * 
     * @return o sistema de agendamento da oficina.
     */
    public Scheduler scheduler() {
        return scheduler;
    }

    /**
     * Retorna o estoque da oficina.
     * 
     * @return o estoque da oficina.
     */
    public Stock stock() {
        return stock;
    }

    /**
     * Retorna uma representação textual da oficina.
     * 
     * @return representação textual da oficina.
     */
    @Override
    public String toString() {
        return String.format("(%s %s %s %s %s)", registry, memberbase, financial, scheduler, stock);
    }
}
