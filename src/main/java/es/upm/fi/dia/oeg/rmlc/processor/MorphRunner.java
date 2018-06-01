package es.upm.fi.dia.oeg.rmlc.processor;

import es.upm.fi.dia.oeg.morph.base.engine.MorphBaseRunner;
import es.upm.fi.dia.oeg.morph.r2rml.rdb.engine.MorphCSVProperties;
import es.upm.fi.dia.oeg.morph.r2rml.rdb.engine.MorphCSVRunnerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;


public class MorphRunner {


    static Logger log = LoggerFactory.getLogger(MorphRunner.class.getPackage().toString());

    public static void runBatch(String mapping, String csv){
        MorphCSVProperties properties = new MorphCSVProperties();
        properties.setMappingDocumentFilePath(mapping.replace("rmlc.ttl","r2rml.ttl"));
        Path path = Paths.get(csv);
        properties.setOutputFilePath(path.getParent().getParent().toAbsolutePath()+"/results/"+path.getFileName().toString().replace(".csv","")+"-results.nt");
        properties.addCSVFile(csv);
        try {
            MorphCSVRunnerFactory runnerFactory = new MorphCSVRunnerFactory();
            MorphBaseRunner runner = runnerFactory.createRunner(properties);
            runner.run();
            log.info("Materialization made correctly");
        } catch(Exception e) {
            e.printStackTrace();
            log.info("Error occured: " + e.getMessage());
        }

    }

}
