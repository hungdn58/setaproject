<?php
namespace App\Model\Entity;

use Cake\ORM\Entity;

/**
 * User Entity.
 *
 * @property int $userId
 * @property string $nickname
 * @property string $address
 * @property string $profileImage
 * @property string $description
 * @property int $gender
 * @property \Cake\I18n\Time $birthday
 * @property string $works
 * @property string $lat
 * @property string $long
 * @property string $createDate
 * @property string $updateDate
 * @property string $delFlg
 * @property \App\Model\Entity\Bookmark[] $bookmarks
 */
class User extends Entity
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
        'userId' => false,
    ];
}
