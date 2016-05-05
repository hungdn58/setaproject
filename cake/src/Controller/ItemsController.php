<?php
namespace App\Controller;

use App\Controller\AppController;

/**
 * Items Controller
 *
 * @property \App\Model\Table\ItemsTable $Items
 */
class ItemsController extends AppController
{

    /**
     * Index method
     *
     * @return \Cake\Network\Response|null
     */
    public function index()
    {
        // $items = $this->paginate($this->Items);
        $this->viewBuilder()->layout('json');

        $data = $this->Items->find('all');

        $output = [];

        if($data) {
            $output = ['result' => 1];
            $array = array();
            $this->loadModel('Users');
            foreach ($data as $post) {

                $user = $this->Users->find()->where(['userId' => $post->userID])->first();

                $array[] = [
                    'post' => [
                        'profileImage'  =>  $user->profileImage,
                        'nickname'  =>  $user->nickname,
                        'address'   =>  $user->address,
                        'replyTo'   =>  ['userID' => $user->userId, 'userName' => $user->nickname],
                        'content'   =>  $post->contents
                    ]
                ];
            }
            $output[] = ['data' => $array];
            $output[] = ['totalCount' => sizeof($data)];
        } else {
            $output = ['result' => 0, 'reason' => 'Không lấy được dữ liệu'];
        }
                
        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');
    }

    /**
     * View method
     *
     * @param string|null $id Item id.
     * @return \Cake\Network\Response|null
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $item = $this->Items->get($id, [
            'contain' => []
        ]);

        $this->set('item', $item);
        $this->set('_serialize', ['item']);
    }

    /**
     * Add method
     *
     * @return \Cake\Network\Response|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $this->viewBuilder()->layout('json');
        $item = $this->Items->newEntity();
        $output = [];
        if ($this->request->is('post')) {
            $item = $this->Items->patchEntity($item, $this->request->data);
            if ($this->Items->save($item)) {
                $this->Flash->success(__('The item has been saved.'));
                $output = ['result' => 1];
                // return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The item could not be saved. Please, try again.'));
                $output[] = ['result' => 0, 'reason' => 'post bi loi'];
            }
            $this->set('data', json_encode($output));
            $this->render('/General/SerializeJson/');
        }
        $this->set(compact('item'));
        $this->set('_serialize', ['item']);
    }

    /**
     * Edit method
     *
     * @param string|null $id Item id.
     * @return \Cake\Network\Response|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $item = $this->Items->get($id, [
            'contain' => []
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {
            $item = $this->Items->patchEntity($item, $this->request->data);
            if ($this->Items->save($item)) {
                $this->Flash->success(__('The item has been saved.'));
                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The item could not be saved. Please, try again.'));
            }
        }
        $this->set(compact('item'));
        $this->set('_serialize', ['item']);
    }

    /**
     * Delete method
     *
     * @param string|null $id Item id.
     * @return \Cake\Network\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->viewBuilder()->layout('json');
        $this->request->allowMethod(['post', 'delete']);
        $output = [];
        $item = $this->Items->find()->where(['itemID' => $id]);
        if ($this->Items->delete($item)) {
            $this->Flash->success(__('The item has been deleted.'));
            $output = ['result' => 1];
        } else {
            $this->Flash->error(__('The item could not be deleted. Please, try again.'));
            $output[] = ['result' => 0, 'reason' => 'post bi loi'];
        }
        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');
    }
}
