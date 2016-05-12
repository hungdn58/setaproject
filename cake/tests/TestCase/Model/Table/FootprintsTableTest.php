<?php
namespace App\Test\TestCase\Model\Table;

use App\Model\Table\FootprintsTable;
use Cake\ORM\TableRegistry;
use Cake\TestSuite\TestCase;

/**
 * App\Model\Table\FootprintsTable Test Case
 */
class FootprintsTableTest extends TestCase
{

    /**
     * Test subject
     *
     * @var \App\Model\Table\FootprintsTable
     */
    public $Footprints;

    /**
     * Fixtures
     *
     * @var array
     */
    public $fixtures = [
        'app.footprints'
    ];

    /**
     * setUp method
     *
     * @return void
     */
    public function setUp()
    {
        parent::setUp();
        $config = TableRegistry::exists('Footprints') ? [] : ['className' => 'App\Model\Table\FootprintsTable'];
        $this->Footprints = TableRegistry::get('Footprints', $config);
    }

    /**
     * tearDown method
     *
     * @return void
     */
    public function tearDown()
    {
        unset($this->Footprints);

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
