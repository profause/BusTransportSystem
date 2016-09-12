<?php
namespace App\Test\TestCase\Model\Table;

use App\Model\Table\DestinationsTable;
use Cake\ORM\TableRegistry;
use Cake\TestSuite\TestCase;

/**
 * App\Model\Table\DestinationsTable Test Case
 */
class DestinationsTableTest extends TestCase
{

    /**
     * Test subject
     *
     * @var \App\Model\Table\DestinationsTable
     */
    public $Destinations;

    /**
     * Fixtures
     *
     * @var array
     */
    public $fixtures = [
        'app.destinations',
        'app.tickets'
    ];

    /**
     * setUp method
     *
     * @return void
     */
    public function setUp()
    {
        parent::setUp();
        $config = TableRegistry::exists('Destinations') ? [] : ['className' => 'App\Model\Table\DestinationsTable'];
        $this->Destinations = TableRegistry::get('Destinations', $config);
    }

    /**
     * tearDown method
     *
     * @return void
     */
    public function tearDown()
    {
        unset($this->Destinations);

        parent::tearDown();
    }

    /**
     * Test initialize method
     *
     * @return void
     */
    public function testInitialize()
    {
        $this->markTestIncomplete('Not implemented yet.');
    }

    /**
     * Test validationDefault method
     *
     * @return void
     */
    public function testValidationDefault()
    {
        $this->markTestIncomplete('Not implemented yet.');
    }
}
