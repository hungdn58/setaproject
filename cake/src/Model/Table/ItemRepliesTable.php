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
        $this->displayField('itemID');
        $this->primaryKey('itemID');
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
            ->integer('itemID')
            ->allowEmpty('itemID', 'create');

        $validator
            ->requirePresence('comments', 'create')
            ->notEmpty('comments');

        $validator
            ->integer('writeUserID')
            ->requirePresence('writeUserID', 'create')
            ->notEmpty('writeUserID');

        $validator
            ->requirePresence('createDate', 'create')
            ->notEmpty('createDate');

        $validator
            ->allowEmpty('updateDate');

        $validator
            ->requirePresence('delFlg', 'create')
            ->notEmpty('delFlg');

        return $validator;
    }
}
