<?php
namespace App\Test\TestCase\Model\Table;

use App\Model\Table\UserChatTable;
use Cake\ORM\TableRegistry;
use Cake\TestSuite\TestCase;

/**
 * App\Model\Table\UserChatTable Test Case
 */
class UserChatTableTest extends TestCase
{

    /**
     * Test subject
     *
     * @var \App\Model\Table\UserChatTable
     */
    public $UserChat;

    /**
     * Fixtures
     *
     * @var array
     */
    public $fixtures = [
        'app.user_chat'
    ];

    /**
     * setUp method
     *
     * @return void
     */
    public function setUp()
    {
        parent::setUp();
        $config = TableRegistry::exists('UserChat') ? [] : ['className' => 'App\Model\Table\UserChatTable'];
        $this->UserChat = TableRegistry::get('UserChat', $config);
    }

    /**
     * tearDown method
     *
     * @return void
     */
    public function tearDown()
    {
        unset($this->UserChat);

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
