#!groovy

@Grapes([
  @GrabResolver(name='mvnRepository', root='http://central.maven.org/maven2/'),
  @Grab(group='org.marc4j', module='marc4j', version='2.8.1'),
  @GrabExclude('org.codehaus.groovy:groovy-all')
])


import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.MarcXmlWriter;
import org.marc4j.converter.impl.AnselToUnicode;
import org.marc4j.marc.Record;
import groovy.json.JsonSlurper

config = null;
cfg_file = new File('./sync-marc-cfg.json')
if ( cfg_file.exists() ) {
  config = new JsonSlurper().parseText(cfg_file.text);
}
else {
  config=[:]
  config.packageData=[:]
}

// Convert input file marcxml to output file marc21
InputStream is = new FileInputStream(args[0])
MarcStreamReader reader = new MarcStreamReader(is);

println("Reading records...");
while (reader.hasNext()) {
  try {
    println("Read Record");
    Record record = reader.next();
    println(record.toString() + "\n************\n");
    // addToGoKB(false, httpbuilder, mods_record)
    // writer.write(record);

    println("done result now contains a DOM tree for the MODS record");
  }
  catch(Exception e) {
    e.printStackTrace()
  }
  finally {
    println("Loop");
  }
}
println("Done converting...");
