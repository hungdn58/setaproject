<?php
namespace App\Controller;

use App\Controller\AppController;

/**
 * UserChat Controller
 *
 * @property \App\Model\Table\UserChatTable $UserChat
 */
class UserChatController extends AppController
{

    /**
     * Index method
     *
     * @return \Cake\Network\Response|null
     */
    public function index()
    {
        $userChat = $this->paginate($this->UserChat);

        $this->set(compact('userChat'));
        $this->set('_serialize', ['userChat']);
    }

    /**
     * View method
     *
     * @param string|null $id User Chat id.
     * @return \Cake\Network\Response|null
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view()
    {
        $this->viewBuilder()->layout('json');

        $id1 = $this->request->query['id1'];
        $id2 = $this->request->query['id2'];
        $limit = $this->request->query['offset'];
        $offset = $this->request->query['limit'];

        $data = $this->UserChat->find('all')->where(['userID1' => $id1, 'userID2' => $id2])->orWhere(['userID1' => $id2, 'userID2' => $id1])->limit($limit);

        $output = [];

        if ($data) {
            $output = ['result' => 1,
                        'opponent' => '/www/profile.jpg'];
            // $output[] = ['opponent' => '/www/profile.jpg'];
            $array = array();
            foreach ($data as $chatItem) {
                $sender = "";
                if($chatItem->userID1 == $chatItem->from){
                    $sender = 'owner';
                }else{
                    $sender = 'other';
                }
                $output[] = [
                    'data' => [
                        'from' => $sender,
                        'content'  =>  $chatItem->message,
                        'posttime'   =>  $chatItem->createDate
                    ]
                ];
            }
            $output[] = ['totalCount' => $data->count()];
        } else {
            $output = ['result' => 0, 'reason' => 'Không lấy được dữ liệu'];
        }
        
        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');
    }

    /**
     * Add method
     *
     * @return \Cake\Network\Response|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $userChat = $this->UserChat->newEntity();
        $output = [];

        if ($this->request->is('post')) {
            // $data = $this->request->data;
            // var_dump($_POST);die();
            $userChat = $this->UserChat->patchEntity($userChat, $this->request->data);
            if ($this->UserChat->save($userChat)) {
                $this->Flash->success(__('The user chat has been saved.'));
                $output = ['result' => 1];
            } else {
                $this->Flash->error(__('The user chat could not be saved. Please, try again.'));
                $output[] = ['result' => 0, 'reason' => 'post bi loi'];
            }
            $this->viewBuilder()->layout('json');
            $this->set('data', json_encode($output));
            $this->render('/General/SerializeJson/');
        }
        $this->set(compact('userChat'));
        $this->set('_serialize', ['userChat']);
    }

    /**
     * Edit method
     *
     * @param string|null $id User Chat id.
     * @return \Cake\Network\Response|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $userChat = $this->UserChat->get($id, [
            'contain' => []
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {
            $userChat = $this->UserChat->patchEntity($userChat, $this->request->data);
            if ($this->UserChat->save($userChat)) {
                $this->Flash->success(__('The user chat has been saved.'));
                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The user chat could not be saved. Please, try again.'));
            }
        }
        $this->set(compact('userChat'));
        $this->set('_serialize', ['userChat']);
    }

    /**
     * Delete method
     *
     * @param string|null $id User Chat id.
     * @return \Cake\Network\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->request->allowMethod(['post', 'delete']);
        $userChat = $this->UserChat->get($id);
        if ($this->UserChat->delete($userChat)) {
            $this->Flash->success(__('The user chat has been deleted.'));
        } else {
            $this->Flash->error(__('The user chat could not be deleted. Please, try again.'));
        }
        return $this->redirect(['action' => 'index']);
    }

    public function chatList() {

        $this->viewBuilder()->layout('json');
        $this->loadModel('Users');

        $userId = $this->request->query['id'];
        $offset = $this->request->query['offset'];
        $limit = $this->request->query['limit'];

        $data = $this->UserChat->find()->where(['userID1' => $userId]);
        
        $output = [];

        if ($data) {
            $output = ['result' => 1];
            $array = array();
            foreach ($data as $chatItem) {
                $user = $this->Users->find()->where(['userId' => $chatItem->userID2])->first();
                $array[] = [
                    'user' => [
                        'profileImage'  =>  $user->profileImage,
                        'nickname'  =>  $user->nickname,
                    ],
                    'content'  =>  $chatItem->message,
                    'posttime'   =>  $chatItem->createDate

                ];
            }
            $output[] = ['data' => $array];
            $output[] = ['totalCount' => $data->count()];
        } else {
            $output = ['result' => 0, 'reason' => 'Không lấy được dữ liệu'];
        }
        
        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');
    }
}
