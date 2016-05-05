<?php
namespace App\Test\TestCase\Model\Table;

use App\Model\Table\ItemRepliesTable;
use Cake\ORM\TableRegistry;
use Cake\TestSuite\TestCase;

/**
 * App\Model\Table\ItemRepliesTable Test Case
 */
class ItemRepliesTableTest extends TestCase
{

    /**
     * Test subject
     *
     * @var \App\Model\Table\ItemRepliesTable
     */
    public $ItemReplies;

    /**
     * Fixtures
     *
     * @var array
     */
    public $fixtures = [
        'app.item_replies'
    ];

    /**
     * setUp method
     *
     * @return void
     */
    public function setUp()
    {
        parent::setUp();
        $config = TableRegistry::exists('ItemReplies') ? [] : ['className' => 'App\Model\Table\ItemRepliesTable'];
        $this->ItemReplies = TableRegistry::get('ItemReplies', $config);
    }

    /**
     * tearDown method
     *
     * @return void
     */
    public function tearDown()
    {
        unset($this->ItemReplies);

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
