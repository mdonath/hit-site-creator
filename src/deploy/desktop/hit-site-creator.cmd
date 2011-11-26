@ECHO OFF
CALL java -jar ${project.build.finalName}-${app.suffix}.${project.packaging} -jaar 2012 -htmlout output -projectcsv input/hit-algemeen.csv -plaatscsv input/formuliergegevens_5686.csv -kampcsv input/formuliergegevens_5687.csv
