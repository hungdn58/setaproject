<?php
namespace App\Test\Fixture;

use Cake\TestSuite\Fixture\TestFixture;

/**
 * UsersFixture
 *
 */
class UsersFixture extends TestFixture
{

    /**
     * Fields
     *
     * @var array
     */
    // @codingStandardsIgnoreStart
    public $fields = [
        'userId' => ['type' => 'integer', 'length' => 11, 'unsigned' => false, 'null' => false, 'default' => null, 'comment' => '', 'precision' => null, 'autoIncrement' => null],
        'nickname' => ['type' => 'string', 'length' => 128, 'null' => false, 'default' => null, 'comment' => '', 'precision' => null, 'fixed' => null],
        'address' => ['type' => 'string', 'length' => 256, 'null' => true, 'default' => null, 'comment' => '', 'precision' => null, 'fixed' => null],
        'profileImage' => ['type' => 'string', 'length' => 45, 'null' => true, 'default' => null, 'comment' => '', 'precision' => null, 'fixed' => null],
        'description' => ['type' => 'string', 'length' => 45, 'null' => true, 'default' => null, 'comment' => '', 'precision' => null, 'fixed' => null],
        'gender' => ['type' => 'integer', 'length' => 11, 'unsigned' => false, 'null' => true, 'default' => '2', 'comment' => '0: female
1: male
2: undefine', 'precision' => null, 'autoIncrement' => null],
        'birthday' => ['type' => 'datetime', 'length' => null, 'null' => true, 'default' => null, 'comment' => '', 'precision' => null],
        'works' => ['type' => 'string', 'length' => 45, 'null' => true, 'default' => null, 'comment' => '', 'precision' => null, 'fixed' => null],
        'lat' => ['type' => 'string', 'length' => 45, 'null' => true, 'default' => null, 'comment' => '', 'precision' => null, 'fixed' => null],
        'long' => ['type' => 'string', 'length' => 45, 'null' => true, 'default' => null, 'comment' => '', 'precision' => null, 'fixed' => null],
        'createDate' => ['type' => 'string', 'length' => 45, 'null' => true, 'default' => null, 'comment' => '', 'precision' => null, 'fixed' => null],
        'updateDate' => ['type' => 'string', 'length' => 45, 'null' => true, 'default' => null, 'comment' => '', 'precision' => null, 'fixed' => null],
        'delFlg' => ['type' => 'string', 'length' => 45, 'null' => true, 'default' => '0', 'comment' => '1: mean delete ', 'precision' => null, 'fixed' => null],
        '_constraints' => [
            'primary' => ['type' => 'primary', 'columns' => ['userId'], 'length' => []],
            'id_UNIQUE' => ['type' => 'unique', 'columns' => ['userId'], 'length' => []],
            'nickname_UNIQUE' => ['type' => 'unique', 'columns' => ['nickname'], 'length' => []],
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
            'userId' => 1,
            'nickname' => 'Lorem ipsum dolor sit amet',
            'address' => 'Lorem ipsum dolor sit amet',
            'profileImage' => 'Lorem ipsum dolor sit amet',
            'description' => 'Lorem ipsum dolor sit amet',
            'gender' => 1,
            'birthday' => '2016-04-27 02:41:23',
            'works' => 'Lorem ipsum dolor sit amet',
            'lat' => 'Lorem ipsum dolor sit amet',
            'long' => 'Lorem ipsum dolor sit amet',
            'createDate' => 'Lorem ipsum dolor sit amet',
            'updateDate' => 'Lorem ipsum dolor sit amet',
            'delFlg' => 'Lorem ipsum dolor sit amet'
        ],
    ];
}
