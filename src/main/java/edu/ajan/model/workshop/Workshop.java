package edu.ajan.model.workshop;

import edu.ajan.model.workshop.common.Registry;
import edu.ajan.model.workshop.financial.Financial;
import edu.ajan.model.workshop.service.Scheduler;
import edu.ajan.model.workshop.staff.MemberBase;
import edu.ajan.model.workshop.stock.Stock;

/**
 * Classe que representa o workshop.
 * 
 * @author Alan Lima
 */
public class Workshop {
    
    /**
     * Registro do workshop, contendo informações sobre o estabelecimento.
     */
    private Registry registry;

    /**
     * Base de usuários do workshop, contendo informações sobre os membros.
     */
    private MemberBase userbase;

    /**
     * Sistema financeiro do workshop, responsável por gerenciar finanças.
     */
    private Financial financial;

    /**
     * Sistema de agendamento do workshop, responsável por gerenciar serviços e
     * horários.
     */
    private Scheduler scheduler;

    /**
     * Estoque do workshop, responsável por gerenciar produtos e suprimentos.
     */
    private Stock stock;

    /**
     * Construtor padrão do workshop.
     */
    public Workshop() {
        this.registry = new Registry();
        this.userbase = new MemberBase();
        this.financial = new Financial();
        this.scheduler = new Scheduler();
        this.stock = new Stock();
    }

    /**
     * Retorna o registro do workshop.
     * 
     * @return o registro do workshop.
     */
    public Registry registry() {
        return registry;
    }

    /**
     * Retorna a base de usuários do workshop.
     * 
     * @return a base de usuários do workshop.
     */
    public MemberBase userbase() {
        return userbase;
    }

    /**
     * Retorna o sistema financeiro do workshop.
     * 
     * @return o sistema financeiro do workshop.
     */
    public Financial financial() {
        return financial;
    }

    /**
     * Retorna o sistema de agendamento do workshop.
     * 
     * @return o sistema de agendamento do workshop.
     */
    public Scheduler scheduler() {
        return scheduler;
    }

    /**
     * Retorna o estoque do workshop.
     * 
     * @return o estoque do workshop.
     */
    public Stock stock() {
        return stock;
    }

    /**
     * Retorna uma representação textual do workshop.
     * 
     * @return representação textual do workshop.
     */
    @Override
    public String toString() {
        return String.format("(%s %s %s %s %s)", registry, userbase, financial, scheduler, stock);
    }
}
