<?php
namespace App\Model\Table;

use App\Model\Entity\Footprint;
use Cake\ORM\Query;
use Cake\ORM\RulesChecker;
use Cake\ORM\Table;
use Cake\Validation\Validator;

/**
 * Footprints Model
 *
 */
class FootprintsTable extends Table
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

        $this->table('footprints');
        $this->displayField('id');
        $this->primaryKey('id');
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
            ->integer('id')
            ->allowEmpty('id', 'create');

        $validator
            ->integer('visitor')
            ->requirePresence('visitor', 'create')
            ->notEmpty('visitor');

        $validator
            ->integer('footprintID')
            ->requirePresence('footprintID', 'create')
            ->notEmpty('footprintID');

        $validator
            ->dateTime('createDate')
            ->allowEmpty('createDate');

        $validator
            ->integer('delFlg')
            ->allowEmpty('delFlg');

        return $validator;
    }
}
