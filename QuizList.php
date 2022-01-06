<?php
    $con = mysqli_connect("localhost", "hun9898", "hunhun98!", "hun9898");
    mysqli_query($con,'SET NAMES utf8');

    $u_id = $_POST["u_id"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE u_id = ?");
    mysqli_stmt_bind_param($statement, "i", $b_id); 
    mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $b_id, $q_que, $q_co, $q_wr1, $q_wr2, $q_wr3); //binding

    $response = array();
    $response["success"] = false;
    
    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["u_id "] = $u_id;
        $response["q_que "] = $q_que;
        $response["q_co"] = $ q_co;
        $response["q_wr1"] = $ q_wr1;
        $response["q_wr2"] = $ q_wr2;
        $response["q_wr2"] = $ q_wr2;
}

    echo json_encode($response);



?>
