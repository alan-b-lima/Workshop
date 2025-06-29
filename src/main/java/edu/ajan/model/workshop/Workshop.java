package edu.ajan.model.workshop;

import edu.ajan.model.workshop.common.Registry;
import edu.ajan.model.workshop.financial.Financial;
import edu.ajan.model.workshop.service.Scheduler;
import edu.ajan.model.workshop.staff.MemberBase;
import edu.ajan.model.workshop.stock.Stock;

public class Workshop {
    
    private Registry registry;

    private MemberBase userbase;

    private Financial financial;

    private Scheduler scheduler;

    private Stock stock;

    public Workshop() {
        this.registry = new Registry();
        this.userbase = new MemberBase();
        this.financial = new Financial();
        this.scheduler = new Scheduler();
        this.stock = new Stock();
    }

    public Registry registry() {
        return registry;
    }

    public MemberBase userbase() {
        return userbase;
    }

    public Financial financial() {
        return financial;
    }

    public Scheduler scheduler() {
        return scheduler;
    }

    public Stock stock() {
        return stock;
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s %s %s)", registry, userbase, financial, scheduler, stock);
    }
}
