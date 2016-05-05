<?php
namespace App\Model\Table;

use App\Model\Entity\UserDevice;
use Cake\ORM\Query;
use Cake\ORM\RulesChecker;
use Cake\ORM\Table;
use Cake\Validation\Validator;

/**
 * UserDevice Model
 *
 */
class UserDeviceTable extends Table
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

        $this->table('user_device');
        $this->displayField('userID');
        $this->primaryKey(['userID', 'deviceID']);
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
            ->integer('userID')
            ->allowEmpty('userID', 'create')
            ->add('userID', 'unique', ['rule' => 'validateUnique', 'provider' => 'table']);

        $validator
            ->allowEmpty('deviceID', 'create')
            ->add('deviceID', 'unique', ['rule' => 'validateUnique', 'provider' => 'table']);

        $validator
            ->allowEmpty('deviceToken');

        $validator
            ->dateTime('createDate')
            ->allowEmpty('createDate');

        $validator
            ->dateTime('updateDate')
            ->allowEmpty('updateDate');

        $validator
            ->integer('delFlg')
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
        $rules->add($rules->isUnique(['userID']));
        $rules->add($rules->isUnique(['deviceID']));
        return $rules;
    }
}
