<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('Edit User Device'), ['action' => 'edit', $userDevice->userID]) ?> </li>
        <li><?= $this->Form->postLink(__('Delete User Device'), ['action' => 'delete', $userDevice->userID], ['confirm' => __('Are you sure you want to delete # {0}?', $userDevice->userID)]) ?> </li>
        <li><?= $this->Html->link(__('List User Device'), ['action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New User Device'), ['action' => 'add']) ?> </li>
    </ul>
</nav>
<div class="userDevice view large-9 medium-8 columns content">
    <h3><?= h($userDevice->userID) ?></h3>
    <table class="vertical-table">
        <tr>
            <th><?= __('DeviceID') ?></th>
            <td><?= h($userDevice->deviceID) ?></td>
        </tr>
        <tr>
            <th><?= __('DeviceToken') ?></th>
            <td><?= h($userDevice->deviceToken) ?></td>
        </tr>
        <tr>
            <th><?= __('UserID') ?></th>
            <td><?= $this->Number->format($userDevice->userID) ?></td>
        </tr>
        <tr>
            <th><?= __('DelFlg') ?></th>
            <td><?= $this->Number->format($userDevice->delFlg) ?></td>
        </tr>
        <tr>
            <th><?= __('CreateDate') ?></th>
            <td><?= h($userDevice->createDate) ?></td>
        </tr>
        <tr>
            <th><?= __('UpdateDate') ?></th>
            <td><?= h($userDevice->updateDate) ?></td>
        </tr>
    </table>
</div>
