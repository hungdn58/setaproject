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

        $offset = $this->request->query['offset'];
        $limit = $this->request->query['limit'];

        $data = $this->Items->find()->limit($limit);

        $output = [];

        if($data) {
            $output['result'] = 1;
            $array = array();
            $this->loadModel('Users');
            foreach ($data as $post) {

                $user = $this->Users->find()->where(['userId' => $post->userID])->first();

                $array[] =
                    [
                        'profileImage'  =>  $user->profileImage,
                        'nickname'  =>  $user->nickname,
                        'address'   =>  $user->address,
                        'birthday'  =>  $user->birthday,
                        'replyTo'   =>  ['userID' => $user->userId, 'userName' => $user->nickname],
                        'content'   =>  $post->contents,
                        'posttime'  =>  $post->createDate,
                        'image'     =>  $post->image,
                        'itemID'    =>  $post->itemID
                    ];
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
        
        $item = $this->Items->newEntity();
        $output = [];
        if ($this->request->is('post')) {
            $data = $this->request->data;
            // var_dump($data['contents']);die();
            $item = $this->Items->patchEntity($item, $this->request->data);
            if ($this->Items->save($item)) {
                $this->Flash->success(__('The item has been saved.'));
                $output['result'] = 1;
                // return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The item could not be saved. Please, try again.'));
                $output['result'] = 0;
                $output['reason'] = 'không lấy được dữ liệu';
            }
            $this->viewBuilder()->layout('json');
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
        $output = [];
        if ($this->request->is(['patch', 'post', 'put'])) {
            $item = $this->Items->patchEntity($item, $this->request->data);
            $item->isReport = true;
            if ($this->Items->save($item)) {
                $this->Flash->success(__('The item has been saved.'));
                $output['result'] = 1;
            } else {
                $this->Flash->error(__('The item could not be saved. Please, try again.'));
                $output['result'] = 0;
                $output['reason'] = 'không lấy được dữ liệu';
            }
            $this->viewBuilder()->layout('json');
            $this->set('data', json_encode($output));
            $this->render('/General/SerializeJson/');
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
        // $this->request->allowMethod(['post', 'delete']);
        $item = $this->Items->get($id, [
            'contain' => []
        ]);
        $output = [];

        if ($this->Items->delete($item)) {
            $this->Flash->success(__('The item has been deleted.'));
            $output['result'] = 1;
        } else {
            $this->Flash->error(__('The item could not be deleted. Please, try again.'));
            $output['result'] = 0;
            $output['reason'] = 'không lấy được dữ liệu';
        }
        $this->viewBuilder()->layout('json');
        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');
    }

    public function getMyItem() {

        $this->viewBuilder()->layout('json');
        $this->loadModel('Users');
        
        $userId = $this->request->query['id'];

        $data = $this->Items->find()->where(['userID' => $userId]);

        $output = [];

        if ($data) {
            $output['result'] = 1;
            $array = array();
            foreach ($data as $item) {
                $user = $this->Users->find()->where(['userId' => $item->userID])->first();
                $array[] = [
                    'profileImage'  =>  $user->profileImage,
                    'nickname'  =>  $user->nickname,
                    'address'   =>  $user->address,
                    'content'   =>  $item->contents,
                    'posttime'  =>  $item->createDate,
                    'itemID'    =>  $item->itemID      
                ];
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
}
