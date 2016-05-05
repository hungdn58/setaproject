<?php
namespace App\Model\Entity;

use Cake\ORM\Entity;

/**
 * ItemReply Entity.
 *
 * @property int $itemID
 * @property string $comments
 * @property int $writeUserID
 * @property string $createDate
 * @property string $updateDate
 * @property string $delFlg
 */
class ItemReply extends Entity
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
        'itemID' => false,
    ];
}
