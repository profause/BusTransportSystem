<?php
namespace App\Controller\Api;
use App\Controller\Api\AppController;
class DestinationsController extends AppController
{
public $paginate = [
'page' => 1,
'limit' => 5,
'maxLimit' => 15,
'fields'=>[
    'id','name'
],
'sortWhitelist' => [
'id', 'name'
]
];
}
?>