<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('Edit User Chat'), ['action' => 'edit', $userChat->chatID]) ?> </li>
        <li><?= $this->Form->postLink(__('Delete User Chat'), ['action' => 'delete', $userChat->chatID], ['confirm' => __('Are you sure you want to delete # {0}?', $userChat->chatID)]) ?> </li>
        <li><?= $this->Html->link(__('List User Chat'), ['action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New User Chat'), ['action' => 'add']) ?> </li>
    </ul>
</nav>
<div class="userChat view large-9 medium-8 columns content">
    <h3><?= h($userChat->chatID) ?></h3>
    <table class="vertical-table">
        <tr>
            <th><?= __('Message') ?></th>
            <td><?= h($userChat->message) ?></td>
        </tr>
        <tr>
            <th><?= __('DelFlg') ?></th>
            <td><?= h($userChat->delFlg) ?></td>
        </tr>
        <tr>
            <th><?= __('ChatID') ?></th>
            <td><?= $this->Number->format($userChat->chatID) ?></td>
        </tr>
        <tr>
            <th><?= __('UserID1') ?></th>
            <td><?= $this->Number->format($userChat->userID1) ?></td>
        </tr>
        <tr>
            <th><?= __('UserID2') ?></th>
            <td><?= $this->Number->format($userChat->userID2) ?></td>
        </tr>
        <tr>
            <th><?= __('From') ?></th>
            <td><?= $this->Number->format($userChat->from) ?></td>
        </tr>
        <tr>
            <th><?= __('IsBlock') ?></th>
            <td><?= $this->Number->format($userChat->isBlock) ?></td>
        </tr>
        <tr>
            <th><?= __('CreateDate') ?></th>
            <td><?= h($userChat->createDate) ?></td>
        </tr>
        <tr>
            <th><?= __('UpdateDate') ?></th>
            <td><?= h($userChat->updateDate) ?></td>
        </tr>
    </table>
</div>
