<?php
namespace App\Controller;

use App\Controller\AppController;

/**
 * Footprints Controller
 *
 * @property \App\Model\Table\FootprintsTable $Footprints
 */
class FootprintsController extends AppController
{

    /**
     * Index method
     *
     * @return \Cake\Network\Response|null
     */
    public function index()
    {
        //$this->Users->findAll();
        $this->viewBuilder()->layout('json');
        //$users = $this->paginate($this->Users);

        $data = $this->Footprints->find('all');
        
        $output = [];
        if($data) {
            $output['result'] = 1;
            $this->loadModel('Users');
            $array = array();
            foreach ($data as $item) {

                $user = $this->Users->find()->where(['userId' => $item->visitor])->first();

                if ($item->footprintID != $item->visitor) {
                    # code...
                    $time = $item->createDate;
                    $time = strtotime($time);
                    $date = date('Y-m-d' , $time);

                    $array[] = [
                        'profileImage' => $user->profileImage,
                        'nickname' => $user->nickname,
                        'address' => $user->address,
                        'posttime' => $date,
                        'userId'  =>  $user->userId
                    ];
                }
                
            }
            $output['data'] = $array;
            $output['totalCount'] = $data->count();
        } else {
            $output['result'] = 0;
            $output['reason'] = 'không lấy được dữ liệu';
        }
                
        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');
    }

    /**
     * View method
     *
     * @param string|null $id Footprint id.
     * @return \Cake\Network\Response|null
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $footprint = $this->Footprints->get($id, [
            'contain' => []
        ]);

        $this->set('footprint', $footprint);
        $this->set('_serialize', ['footprint']);
    }

    /**
     * Add method
     *
     * @return \Cake\Network\Response|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $output = [];

        $footprint = $this->Footprints->newEntity();
        if ($this->request->is('post')) {
            $footprint = $this->Footprints->patchEntity($footprint, $this->request->data);
            if ($this->Footprints->save($footprint)) {
                $this->Flash->success(__('The footprint has been saved.'));
                $output['result'] = 1;
            } else {
                $this->Flash->error(__('The footprint could not be saved. Please, try again.'));
                $output['result'] = 0;
                $output['reason'] = 'không lấy được dữ liệu';
            }
            $this->viewBuilder()->layout('json');
            $this->set('data', json_encode($output));
            $this->render('/General/SerializeJson/');
        }
        $this->set(compact('footprint'));
        $this->set('_serialize', ['footprint']);
    }

    /**
     * Edit method
     *
     * @param string|null $id Footprint id.
     * @return \Cake\Network\Response|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $footprint = $this->Footprints->get($id, [
            'contain' => []
        ]);
        $output = [];
        if ($this->request->is(['patch', 'post', 'put'])) {
            $footprint = $this->Footprints->patchEntity($footprint, $this->request->data);
            if ($this->Footprints->save($footprint)) {
                $this->Flash->success(__('The footprint has been saved.'));
                $output['result'] = 1;
            } else {
                $this->Flash->error(__('The footprint could not be saved. Please, try again.'));
                $output['result'] = 0;
                $output['reason'] = 'không lấy được dữ liệu';
            }
            $this->viewBuilder()->layout('json');
            $this->set('data', json_encode($output));
            $this->render('/General/SerializeJson/');
        }
        $this->set(compact('footprint'));
        $this->set('_serialize', ['footprint']);
    }

    /**
     * Delete method
     *
     * @param string|null $id Footprint id.
     * @return \Cake\Network\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->request->allowMethod(['post', 'delete']);
        $footprint = $this->Footprints->get($id);
        if ($this->Footprints->delete($footprint)) {
            $this->Flash->success(__('The footprint has been deleted.'));
        } else {
            $this->Flash->error(__('The footprint could not be deleted. Please, try again.'));
        }
        return $this->redirect(['action' => 'index']);
    }
}
