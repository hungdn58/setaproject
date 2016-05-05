<?php
namespace App\Test\Fixture;

use Cake\TestSuite\Fixture\TestFixture;

/**
 * UserDeviceFixture
 *
 */
class UserDeviceFixture extends TestFixture
{

    /**
     * Table name
     *
     * @var string
     */
    public $table = 'user_device';

    /**
     * Fields
     *
     * @var array
     */
    // @codingStandardsIgnoreStart
    public $fields = [
        'userID' => ['type' => 'integer', 'length' => 11, 'unsigned' => false, 'null' => false, 'default' => null, 'comment' => '', 'precision' => null, 'autoIncrement' => null],
        'deviceID' => ['type' => 'string', 'length' => 128, 'null' => false, 'default' => null, 'comment' => '', 'precision' => null, 'fixed' => null],
        'deviceToken' => ['type' => 'string', 'length' => 256, 'null' => true, 'default' => null, 'comment' => '', 'precision' => null, 'fixed' => null],
        'createDate' => ['type' => 'datetime', 'length' => null, 'null' => true, 'default' => null, 'comment' => '', 'precision' => null],
        'updateDate' => ['type' => 'datetime', 'length' => null, 'null' => true, 'default' => null, 'comment' => '', 'precision' => null],
        'delFlg' => ['type' => 'integer', 'length' => 11, 'unsigned' => false, 'null' => true, 'default' => '0', 'comment' => '1: xoa 
', 'precision' => null, 'autoIncrement' => null],
        '_constraints' => [
            'primary' => ['type' => 'primary', 'columns' => ['userID', 'deviceID'], 'length' => []],
            'id_UNIQUE' => ['type' => 'unique', 'columns' => ['userID'], 'length' => []],
            'nickname_UNIQUE' => ['type' => 'unique', 'columns' => ['deviceID'], 'length' => []],
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
            'userID' => 1,
            'deviceID' => 'ebe3e7aa-a2e4-47a9-a3df-1047bb5520ad',
            'deviceToken' => 'Lorem ipsum dolor sit amet',
            'createDate' => '2016-04-27 02:40:54',
            'updateDate' => '2016-04-27 02:40:54',
            'delFlg' => 1
        ],
    ];
}
