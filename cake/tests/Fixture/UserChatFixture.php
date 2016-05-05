<?php
namespace App\Test\Fixture;

use Cake\TestSuite\Fixture\TestFixture;

/**
 * UserChatFixture
 *
 */
class UserChatFixture extends TestFixture
{

    /**
     * Table name
     *
     * @var string
     */
    public $table = 'user_chat';

    /**
     * Fields
     *
     * @var array
     */
    // @codingStandardsIgnoreStart
    public $fields = [
        'chatID' => ['type' => 'integer', 'length' => 11, 'unsigned' => false, 'null' => false, 'default' => null, 'comment' => '', 'precision' => null, 'autoIncrement' => null],
        'userID1' => ['type' => 'integer', 'length' => 11, 'unsigned' => false, 'null' => false, 'default' => null, 'comment' => '', 'precision' => null, 'autoIncrement' => null],
        'userID2' => ['type' => 'integer', 'length' => 11, 'unsigned' => false, 'null' => false, 'default' => null, 'comment' => '', 'precision' => null, 'autoIncrement' => null],
        'message' => ['type' => 'string', 'length' => 512, 'null' => false, 'default' => null, 'comment' => '', 'precision' => null, 'fixed' => null],
        'from' => ['type' => 'integer', 'length' => 11, 'unsigned' => false, 'null' => false, 'default' => null, 'comment' => 'from: define by userID
', 'precision' => null, 'autoIncrement' => null],
        'createDate' => ['type' => 'datetime', 'length' => null, 'null' => false, 'default' => null, 'comment' => '', 'precision' => null],
        'updateDate' => ['type' => 'datetime', 'length' => null, 'null' => false, 'default' => null, 'comment' => '', 'precision' => null],
        'delFlg' => ['type' => 'string', 'length' => 45, 'null' => true, 'default' => '0', 'comment' => '', 'precision' => null, 'fixed' => null],
        'isBlock' => ['type' => 'integer', 'length' => 11, 'unsigned' => false, 'null' => true, 'default' => '0', 'comment' => 'isBlock: block user or not', 'precision' => null, 'autoIncrement' => null],
        '_constraints' => [
            'primary' => ['type' => 'primary', 'columns' => ['chatID'], 'length' => []],
        ],
        '_options' => [
            'engine' => 'InnoDB',
            'collation' => 'utf8_general_ci'
        ],
    ];
    // @codingStandardsIgnoreEnd

    /**
     * Records
     *
     * @var array
     */
    public $records = [
        [
            'chatID' => 1,
            'userID1' => 1,
            'userID2' => 1,
            'message' => 'Lorem ipsum dolor sit amet',
            'from' => 1,
            'createDate' => '2016-04-27 02:40:42',
            'updateDate' => '2016-04-27 02:40:42',
            'delFlg' => 'Lorem ipsum dolor sit amet',
            'isBlock' => 1
        ],
    ];
}
