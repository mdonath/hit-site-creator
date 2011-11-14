<html>

<head>
<title></title>
</head>

<body>
<?php

include_once "soap_class.php";


//$client = new SolSoapClient('hit',$keypriv,'http://dev1.scouting.nl/frontend/sol/sol_dev1.wsdl');
$client = new SolSoapClient('hit',$keypriv,'http://sol.scouting.nl/frontend/sol/sol.wsdl');

try {
    $result = $client->signOn($_GET["usr"], $_GET["passwd"]);
    $per_id = $result['per_id'];

    $role = $_GET["rolused"];
    $frm_id = $_GET["form"];
    $datatype = $_GET["datatype"];
    $datasoort = $_GET["datasoort"];
    echo "<br>$frm_id<br>$role<br>";
    switchRole($client, $role);
    if ($frm_id < 9900) {

// http://www.verster.com/scout/soap_downl.php?usr=gerbrnm&passwd=ww&form=6009&rolused=51,600156678,35,9121,10,1&datatype=team&datasoort=list 

      if ("part_data" == "$datatype") {
        $form_data = $client->doTAB("as_form", "$datasoort", "$datatype",array('frm_id'=>$frm_id, 'prt_st_id'=>array(1000,1003,1004,1005,1009,1011)));
      } else {
        $form_data = $client->doTAB("as_form", "$datasoort", "$datatype",array('frm_id'=>$frm_id));
      }
      echo "doTAB(\"as_form\", \"$datasoort\", \"$datatype\",array('frm_id'=>$frm_id))<br><br>\n\n";
      echo $form_data;
    } else {
//      $form_data = $client->doTAB("hrm_employee", "list", "export",array('hag_Id'=>array(9202)));
// een get als  https://sol.scouting.nl/hrm/agency/2567/candidates?export=true
      $form_data = file_get_contents('https://sol.scouting.nl/hrm/agency/2567/candidates?export=true');
      echo $form_data;
    }
    $result = $client->logout();


} catch (Exception $e) {

    echo 'An exception occured: ' . $e->getMessage();
    print_r($e);
    echo "===========";
    phpinfo();
}

function switchRole($client, $role) {
    $switched_role = $client->doTAB("ma_function", "edit", "changeRole", array('role_id'=>$role));
    $document = DomDocument::loadXML($switched_role);
    //$document->formatOutput = true;
    //echo "<br/><br/>New role: " . $GLOBALS['startTag'] . $document->saveXML() . $GLOBALS['stopTag'];
    $XPath = new DomXPath($document);
    $nodes = $XPath->query("//result/var[@name='sess_id']");
    foreach ($nodes as $node) {
        //echo "new_sess_id: " . $node->textContent;
        $client->pub_session_id = $node->textContent;
    }
}

function getSecPrimeId($client) {
    $environment_vars = $client->doTAB("ma_function", "view", "currentRole");
    $document = DomDocument::loadXML($environment_vars);
    //$document->formatOutput = true;
    //echo "<br/><br/>Environment vars: " . $GLOBALS['startTag'] . $document->saveXML() . $GLOBALS['stopTag'];
    $XPath = new DomXPath($document);
    $sec_prime_id_node = $XPath->query("//result/var[@name='sec_prime_id']/value");
    return $sec_prime_id_node->item(0)->textContent;
}

function getParticipantData($client, $frm_id) {
    $form_data = $client->doTAB("as_form", "report", "part_data",array('frm_id'=>$frm_id, 'prt_st_id'=>array(1009)));
    $document = DomDocument::loadXML($form_data);
    $document->formatOutput = true;
    echo "<br/><br/>Data of the form: " . $GLOBALS['startTag'] . $document->saveXML() . $GLOBALS['stopTag'];
}
//       $form_data = $client->doTAB("as_form", "report", "part_data",array('frm_id'=>$frm_id, 'prt_st_id'=>array(1000,1009)));
?>

</body>

</html>

