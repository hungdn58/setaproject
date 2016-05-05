<?php
namespace App\Model\Entity;

use Cake\ORM\Entity;

/**
 * UserChat Entity.
 *
 * @property int $chatID
 * @property int $userID1
 * @property int $userID2
 * @property string $message
 * @property int $from
 * @property \Cake\I18n\Time $createDate
 * @property \Cake\I18n\Time $updateDate
 * @property string $delFlg
 * @property int $isBlock
 */
class UserChat extends Entity
{

    /**
     * Fields that can be mass assigned using newEntity() or patchEntity().
     *
     * Note that when '*' is set to true, this allows all unspecified fields to
     * be mass assigned. For security purposes, it is advised to set '*' to false
     * (or remove it), and explicitly make individual fields accessible as needed.
     *
     * @var array
     */
    protected $_accessible = [
        '*' => true,
        'chatID' => false,
    ];
}
