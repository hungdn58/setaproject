<?php
namespace App\Model\Table;

use App\Model\Entity\User;
use Cake\ORM\Query;
use Cake\ORM\RulesChecker;
use Cake\ORM\Table;
use Cake\Validation\Validator;

/**
 * Users Model
 *
 */
class UsersTable extends Table
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

        $this->table('users');
        $this->displayField('userId');
        $this->primaryKey('userId');
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
            ->integer('userId')
            ->allowEmpty('userId', 'create')
            ->add('userId', 'unique', ['rule' => 'validateUnique', 'provider' => 'table']);

        $validator
            ->requirePresence('nickname', 'create')
            ->notEmpty('nickname')
            ->add('nickname', 'unique', ['rule' => 'validateUnique', 'provider' => 'table']);

        $validator
            ->allowEmpty('address');

        $validator
            ->allowEmpty('profileImage');

        $validator
            ->allowEmpty('description');

        $validator
            ->allowEmpty('gender');

        $validator
            ->allowEmpty('birthday');

        $validator
            ->allowEmpty('works');

        $validator
            ->allowEmpty('lat');

        $validator
            ->allowEmpty('long');

        $validator
            ->allowEmpty('createDate');

        $validator
            ->allowEmpty('updateDate');

        $validator
            ->allowEmpty('delFlg');

        return $validator;
    }

    /**
     * Returns a rules checker object that will be used for validating
     * application integrity.
     *
     * @param \Cake\ORM\RulesChecker $rules The rules object to be modified.
     * @return \Cake\ORM\RulesChecker
     */
    public function buildRules(RulesChecker $rules)
    {
        $rules->add($rules->isUnique(['userId']));
        $rules->add($rules->isUnique(['nickname']));
        return $rules;
    }
}
