<?php
namespace App\Model\Table;

use App\Model\Entity\ItemReply;
use Cake\ORM\Query;
use Cake\ORM\RulesChecker;
use Cake\ORM\Table;
use Cake\Validation\Validator;

/**
 * ItemReplies Model
 *
 */
class ItemRepliesTable extends Table
{

    /**
     * Initialize method
     *
     * @param array $config The configuration for the Table.
     * @return void
     */
    public function initialize(array $config)
    {
        parent::initialize($config);

        $this->table('item_replies');
        $this->displayField('itemReplyID');
        $this->primaryKey('itemReplyID');
    }

    /**
     * Default validation rules.
     *
     * @param \Cake\Validation\Validator $validator Validator instance.
     * @return \Cake\Validation\Validator
     */
    public function validationDefault(Validator $validator)
    {
        $validator
            ->integer('itemReplyID')
            ->allowEmpty('itemReplyID', 'create');

        $validator
            ->integer('itemID')
            ->notEmpty('itemID', 'create');

        $validator
            ->requirePresence('comments', 'create')
            ->notEmpty('comments');

        $validator
            ->integer('writeUserID')
            ->requirePresence('writeUserID', 'create')
            ->notEmpty('writeUserID');

        $validator
            ->allowEmpty('createDate');
        $validator
            ->allowEmpty('updateDate');
        $validator
            ->allowEmpty('delFlg');

        return $validator;
    }
}
