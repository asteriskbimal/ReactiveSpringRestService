package com.reactive.couchbase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args){

        SpringApplication application = new SpringApplication(Application.class);
        application.run("");

    }

    @Override
    public void run(String... args) {
        TABLEP tablep1=new TABLEP();
        tablep1.setId("1");
        tablep1.setAddSeq("1");
        tablep1.setAddress("address 1");
        tablep1.setCompany("company 1");

        TABLEP tablep2=new TABLEP();
        tablep2.setId("2");
        tablep2.setAddSeq("2");
        tablep2.setAddress("address 2");
        tablep2.setCompany("company 2");

        TABLEP tablep3=new TABLEP();
        tablep3.setId("3");
        tablep3.setAddSeq("3");
        tablep3.setAddress("address 3");
        tablep3.setCompany("company 3");

        TABLEP tablep4=new TABLEP();
        tablep4.setId("4");
        tablep4.setAddSeq("4");
        tablep4.setAddress("address 4");
        tablep4.setCompany("company 4");

        TABLEQ tableq1 =new TABLEQ();
        tableq1.setId("1");
        tableq1.setContactId("1");
        tableq1.setSeq("1");

        TABLEQ tableq2 =new TABLEQ();
        tableq2.setId("2");
        tableq2.setContactId("2");
        tableq2.setSeq("2");

        TABLEQ tableq3 =new TABLEQ();
        tableq3.setId("3");
        tableq3.setContactId("3");
        tableq3.setSeq("3");

        TABLER tabler1=new TABLER();
        tabler1.setId("1");
        tabler1.setSeq("1");
        tabler1.setContactId("1");

        TABLER tabler2=new TABLER();
        tabler2.setId("2");
        tabler2.setSeq("2");
        tabler2.setContactId("2");

        List<TABLEP> listOftablep =new ArrayList<>();
        listOftablep.add(tablep1);
        listOftablep.add(tablep2);
        listOftablep.add(tablep3);
        listOftablep.add(tablep4);

        List<TABLEQ> listOftableq =new ArrayList<>();
        listOftableq.add(tableq1);
        listOftableq.add(tableq2);
        listOftableq.add(tableq3);

        List<TABLER> listOftabler =new ArrayList<>();
        listOftabler.add(tabler1);
        listOftabler.add(tabler2);

        Seq<TABLEP> tablepSeq = Seq.seq(listOftablep);
        Seq<TABLEQ> tableqSeq = Seq.seq(listOftableq);
        Seq<TABLER> tablerSeq = Seq.seq(listOftabler);
        tablepSeq.leftOuterJoin(tableqSeq,(a,b)->a.getId().equals(b.getId()))
                .leftOuterJoin(tablerSeq,(a,b)->a.v1.getId().equals(b.getId()))
                .collect(Collectors.toCollection(ArrayList::new))
                .stream().filter((a)->a.v1.v1.getId().equals("1"))
                .forEach(System.out::println);
    } 
    
    class TABLEP {
    private String id;
    private String addSeq;
    private String address;
    private String company;
    }
    
    class TABLEQ {
    private String id;
    private String seq;
    private String contactId;
    }
    
    class TABLER {
    private String id;
    private String seq;
    private String contactId;
    }

   
}
