<?php
namespace App\Model\Table;

use App\Model\Entity\UserChat;
use Cake\ORM\Query;
use Cake\ORM\RulesChecker;
use Cake\ORM\Table;
use Cake\Validation\Validator;

/**
 * UserChat Model
 *
 */
class UserChatTable extends Table
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

        $this->table('user_chat');
        $this->displayField('chatID');
        $this->primaryKey('chatID');
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
            ->integer('chatID')
            ->allowEmpty('chatID', 'create');

        $validator
            ->integer('userID1')
            ->requirePresence('userID1', 'create')
            ->notEmpty('userID1');

        $validator
            ->integer('userID2')
            ->requirePresence('userID2', 'create')
            ->notEmpty('userID2');

        $validator
            ->requirePresence('message', 'create')
            ->notEmpty('message');

        $validator
            ->integer('fromUser')
            ->requirePresence('fromUser', 'create')
            ->notEmpty('fromUser');

        $validator
            ->dateTime('createDate')
            ->allowEmpty('createDate');

        $validator
            ->dateTime('updateDate')
            ->allowEmpty('updateDate');

        $validator
            ->allowEmpty('delFlg');

        $validator
            ->integer('isBlock')
            ->allowEmpty('isBlock');

        return $validator;
    }
}
