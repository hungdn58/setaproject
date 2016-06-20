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
        $limit = $this->request->query['limit'];
        $offset = $this->request->query['offset'];

        $data = $this->UserChat->find('all')->where(['userID1' => $id1, 'userID2' => $id2])->orWhere(['userID1' => $id2, 'userID2' => $id1])->limit($limit);

        $output = [];

        if ($data) {
            $output['result'] = 1;
            $output['opponent'] = '/www/profile.jpg';
            // $output[] = ['opponent' => '/www/profile.jpg'];
            $array = array();
            $this->loadModel('Users');
            foreach ($data as $chatItem) {
                $sender = "";
                if($chatItem->userID1 == $chatItem->fromUser){
                    $sender = 'owner';
                }else{
                    $sender = 'other';
                }

                $user = $this->Users->find()->where(['userId' => $chatItem->fromUser])->first();

                $time = $chatItem->createDate;
                $time = strtotime($time);
                $hour = date('H', $time);
                $minute = date('i', $time);
                $posttime = $hour.":".$minute;

                $array[] = [
                    'from' => $sender,
                    'content'  =>  $chatItem->message,
                    'posttime'   =>  $posttime,
                    'fromID' => $chatItem->fromUser,
                    'profileImage' => $user->profileImage
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
             // var_dump($data);die();
            $userChat = $this->UserChat->patchEntity($userChat, $this->request->data);
            if ($this->UserChat->save($userChat)) {
                $this->Flash->success(__('The user chat has been saved.'));
                $output['result'] = 1;
            } else {
                $this->Flash->error(__('The user chat could not be saved. Please, try again.'));
                $output['result'] = 0;
                $output['reason'] = 'không lấy được dữ liệu';
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

        $data = $this->UserChat->find()->where(['userID1' => $userId])->limit($limit);
        
        $output = [];

        if ($data) {
            $output['result'] = 1;
            $array = array();
            foreach ($data as $chatItem) {
                $user = $this->Users->find()->where(['userId' => $chatItem->userID2])->first();

                $time = $chatItem->createDate;
                $time = strtotime($time);
                $posttime = date('Y-m-d' , $time);

                $array[] = [
                    'content'  =>  $chatItem->message,
                    'posttime'   =>  $posttime,
                    'profileImage'  =>  $user->profileImage,
                    'nickname'  =>  $user->nickname,   
                    'friend_id' => $user->userId     
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
