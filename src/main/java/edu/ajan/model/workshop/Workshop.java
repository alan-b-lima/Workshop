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

    private static Workshop workshop;

    public Workshop() {
        this.registry = new Registry();
        this.userbase = new MemberBase();
        this.financial = new Financial();
        this.scheduler = new Scheduler();
        this.stock = new Stock();
    }

    public static Object registry() {
        return workshop.registry; 
    }

    public static Object userbase() {
        return workshop.userbase;
    }

    public static Object financial() {
        return workshop.financial;
    }

    public static Scheduler scheduler() {
        return workshop.scheduler;
    }

    public static Stock stock() {
        return workshop.stock;
    }
}
