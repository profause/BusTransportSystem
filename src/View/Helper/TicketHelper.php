<?php

namespace App\View\Helper;

use Cake\View\Helper;

class TicketHelper extends Helper
{
public function sold($value=0)
{
	$s='';
	
	if($value==0){
		
		$s='No';
	}
	else if($value==1){
		
		$s='Yes';
	}
    return $s;
	
}
}

?>