<?php
namespace App\Test\TestCase\Model\Table;

use App\Model\Table\UserDeviceTable;
use Cake\ORM\TableRegistry;
use Cake\TestSuite\TestCase;

/**
 * App\Model\Table\UserDeviceTable Test Case
 */
class UserDeviceTableTest extends TestCase
{

    /**
     * Test subject
     *
     * @var \App\Model\Table\UserDeviceTable
     */
    public $UserDevice;

    /**
     * Fixtures
     *
     * @var array
     */
    public $fixtures = [
        'app.user_device'
    ];

    /**
     * setUp method
     *
     * @return void
     */
    public function setUp()
    {
        parent::setUp();
        $config = TableRegistry::exists('UserDevice') ? [] : ['className' => 'App\Model\Table\UserDeviceTable'];
        $this->UserDevice = TableRegistry::get('UserDevice', $config);
    }

    /**
     * tearDown method
     *
     * @return void
     */
    public function tearDown()
    {
        unset($this->UserDevice);

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

    /**
     * Test buildRules method
     *
     * @return void
     */
    public function testBuildRules()
    {
        $this->markTestIncomplete('Not implemented yet.');
    }
}
