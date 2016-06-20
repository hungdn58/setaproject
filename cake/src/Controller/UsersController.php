<?php
namespace App\Controller;

use App\Controller\AppController;
use Cake\ORM\TableRegistry;

/**
 * Users Controller
 *
 * @property \App\Model\Table\UsersTable $Users
 */
class UsersController extends AppController
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

        //$this->set(compact('users'));
        //$this->set('_serialize', ['users']);
        // $data = Array(
        //     "name" => "Saad Imran",
        //     "age" => 19
        // );
        $data = $this->Users->find('all');
        
        $output = [];
        if($data) {
            $output['result'] = 1;
            $array = array();
            foreach ($data as $user) {
                $array[] = [
                    'profileImage'  =>  $user->profileImage,
                    'nickname'  =>  $user->nickname,
                    'uid'   =>  $user->uid,
                    'userId' => $user->userId
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

    public function search()
    {
        $this->viewBuilder()->layout('json');
        $gender = $this->request->query['gender'];
        $year_from = $this->request->query['year_from'];
        $year_to = $this->request->query['year_to'];

        // $data = $this->Users->find()->where(['dateDiff(NOW(),Users.birthday) >' => $year_from])
        //                             ->andWhere(['dateDiff(NOW(),Users.birthday) <' => $year_to])
        $data = $this->Users->find()->andWhere(['Users.gender' => $gender])->andWhere(['Users.birthday <' => $year_to])->andWhere(['Users.birthday >' => $year_from]);

        $output = [];
        if($data) {
            $output['result'] = 1;
            $array = array();

            foreach ($data as $user) {

                $array[] = [
                    'profileImage'  =>  $user->profileImage,
                    'userId'  =>  $user->userId,
                    'nickname' => $user->nickname,
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
     * @param string|null $id User id.
     * @return \Cake\Network\Response|null
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $user = $this->Users->find()->where(['userId' => $id])->first();

        $this->set('user', $user);
        $this->set('_serialize', ['user']);
    }

    /**
     * Add method
     *
     * @return \Cake\Network\Response|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $user = $this->Users->newEntity();
        $output = [];
        if ($this->request->is('post')) {
            $user = $this->Users->patchEntity($user, $this->request->data);
            if ($this->Users->save($user)) {
                $this->Flash->success(__('The user has been saved.'));
                $output['result'] = 1;
                $output['userId'] = $user->userId;
                $output['profileImage'] = $user->profileImage;
            } else {
                $this->Flash->error(__('The user could not be saved. Please, try again.'));
                $output['result'] = 0;
                $output['reason'] = 'không lấy được dữ liệu';
            }
            $this->viewBuilder()->layout('json');
            $this->set('data', json_encode($output));
            $this->render('/General/SerializeJson/');
        } 
        $this->set(compact('user'));
        $this->set('_serialize', ['user']);
    }

    /**
     * Edit method
     *
     * @param string|null $id User id.
     * @return \Cake\Network\Response|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($userId = null)
    {

        $user = $this->Users->get($userId);
       
        $output = [];

        if ($this->request->is(['patch', 'post', 'put'])) {
            // var_dump($_POST);die();
            $data = $this->request->data;
            
            $user = $this->Users->patchEntity($user, $this->request->data);
            if ($this->Users->save($user)) {
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
        $this->set(compact('user'));
        $this->set('_serialize', ['user']);
    }

    /**
     * Delete method
     *
     * @param string|null $id User id.
     * @return \Cake\Network\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->viewBuilder()->layout('json');
        // $this->request->allowMethod(['post', 'delete']);
        $user = $this->Users->get($id);
        $output = [];
        // var_dump($_POST);die();
        if ($this->Users->delete($user)) {
            $this->Flash->success(__('The user has been deleted.'));
            $output['result'] = 1;
        } else {
            $this->Flash->error(__('The user could not be deleted. Please, try again.'));
            $output['result'] = 0;
            $output['reason'] = 'không lấy được dữ liệu';
        }
        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');
    }

    public function getProfile($userId = null)
    {
        $this->viewBuilder()->layout('json');

        $user = $this->Users->find()->where(['userId' => $userId])->first();
        $output = [];

        if ($user) {
            # code...
            $output['result'] = 1;
            $array = array();
            $output['data'] = [
                'profileImage' => $user->profileImage,
                'nickname' => $user->nickname,
                'gender' => $user->gender,
                'birthday' => $user->birthday,
                'address' => $user->address,
                'description' => $user->description,
                'userId' =>$user->userId
            ];
        }else{
            $output['result'] = 0;
            $output['reason'] = 'không lấy được dữ liệu';
        }

        $this->set('data', json_encode($output));
        $this->render('/General/SerializeJson/');
    }

    public function updateID($nickname = null) {

        $output = [];
        $user = $this->Users->find()->where(['nickname' => $nickname])->first();

        if ($this->request->is(['patch', 'post', 'put'])) {
            
            $data = $this->request->data;
            $email = $data['nickname'];
            $uid = $data['uid'];
            // var_dump($_POST);die();
            
            $user = $this->Users->patchEntity($user, $this->request->data);

            if ($this->Users->save($user)) {
                $output['result'] = 1;
                $output['userId'] = $user->userId;
                // return $this->redirect(['action' => 'index']);
            } else {
                $output['result'] = 0;
                $output['reason'] = 'không lấy được dữ liệu';
            }
            $this->viewBuilder()->layout('json');
            $this->set('data', json_encode($output));
            $this->render('/General/SerializeJson/');
        }
        $this->set(compact('user'));
        $this->set('_serialize', ['user']);
    }

}
